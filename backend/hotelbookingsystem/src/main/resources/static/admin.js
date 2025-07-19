document.addEventListener("DOMContentLoaded", () => {
  const username = localStorage.getItem("username");
  if (!username) {
    alert("You must be logged in.");
    window.location.href = "index.html";
  }

  fetch("http://localhost:8080/api/bookings")
    .then(res => res.json())
    .then(bookings => {
      const table = document.getElementById("bookingTable");
      table.innerHTML = "";

      bookings.forEach(b => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${b.id}</td>
          <td>${b.user.username}</td>
          <td>${b.roomType.roomType}</td>
          <td><input type="date" value="${b.startDate}" id="start-${b.id}"></td>
          <td><input type="date" value="${b.endDate}" id="end-${b.id}"></td>
          <td><input type="text" value="${b.guestName}" id="guest-${b.id}"></td>
          <td><input type="number" value="${b.numRooms}" min="1" id="rooms-${b.id}"></td>
          <td>
            <select id="status-${b.id}">
              <option value="booked" ${b.bookingStatus === 'booked' ? 'selected' : ''}>booked</option>
              <option value="cancelled" ${b.bookingStatus === 'cancelled' ? 'selected' : ''}>cancelled</option>
            </select>
          </td>
          <td>
            <button onclick="updateBooking(${b.id})">Update</button>
            <button onclick="deleteBooking(${b.id})">Delete</button>
          </td>
        `;
        table.appendChild(row);
      });
    })
    .catch(err => {
      console.error(err);
      alert("Failed to fetch booking data.");
    });
});

function updateBooking(bookingId) {
  const startDate = document.getElementById(`start-${bookingId}`).value;
  const endDate = document.getElementById(`end-${bookingId}`).value;
  const guestName = document.getElementById(`guest-${bookingId}`).value;
  const numRooms = parseInt(document.getElementById(`rooms-${bookingId}`).value, 10);
  const bookingStatus = document.getElementById(`status-${bookingId}`).value;

  fetch(`http://localhost:8080/api/bookings/${bookingId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      startDate,
      endDate,
      guestName,
      numRooms,
      bookingStatus,
    }),
  })
    .then(res => {
      if (!res.ok) throw new Error("Update failed.");
      return res.text();
    })
    .then(() => {
      alert("Booking updated successfully!");
      window.location.reload();
    })
    .catch(err => {
      console.error(err);
      alert("Error updating booking.");
    });
}

function deleteBooking(bookingId) {
  if (!confirm("Are you sure you want to delete this booking?")) return;

  fetch(`http://localhost:8080/api/bookings/${bookingId}`, {
    method: "DELETE",
  })
    .then(res => {
      if (!res.ok) throw new Error("Delete failed.");
      alert("Booking deleted.");
      window.location.reload();
    })
    .catch(err => {
      console.error(err);
      alert("Error deleting booking.");
    });
}
