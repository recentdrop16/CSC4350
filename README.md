# CSC4350
Project Group
Summary of Changes and Features Added
 Model Layer
Booking: added isCancelled flag and overlaps() method for cancellation and conflict checking.

Room: added capacity and features fields for extended room details.

User: prepared for enum-based roles (e.g., CUSTOMER, EMPLOYEE).

Added Role enum for cleaner role handling (optional).



Repository Layer
BookingRepository:

findBookedRoomIdsInRange(...) to detect date conflicts.

findAllUpcomingBookings(...) for admin use.

RoomRepository:

findByIdNotIn(...) to filter out booked rooms.

findByAvailability(...) to retrieve available rooms.

UserRepository:

existsByUsername and existsByEmail for frontend validation support.

Service Layer
Switched all services to constructor-based dependency injection.

BookingService now uses createBooking(...) and includes booking lookups.

RoomService supports availability filtering by date or general status.

UserService handles registration and user lookups by email or username.



Controller Layer
RoomController:

GET /available: shows available rooms by date.

POST, DELETE: create and remove rooms.

BookingController:

POST /bookings: creates a new booking.

GET /bookings/user/{username}: shows bookings for a user.

UserController:

POST /register: register a user.

GET /{username}, GET /email/{email}: get users by identifier.

PaymentController:

POST, GET /booking/{id}: manage payment records.



Application Startup
Timezone is now set to UTC using CommandLineRunner in the main application file.

Removed @PostConstruct to support Java 11+ compatibility.

Console output confirms timezone on startup.



Final Features
Prevents overlapping bookings based on check-in/check-out dates.

Supports booking cancellation and employee access to all bookings.

Room browsing with dynamic availability and detailed room info.

Payment lookup and creation tied to bookings.

Consistent timezone behavior across environments.