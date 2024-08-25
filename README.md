
# Bug Tracker

## Overview
The Bug Tracker is a software solution designed to streamline the process of bug identification, tracking, and resolution. It allows testers to report bugs, developers to fix them, in-charges to supervise the progress, and admins to manage users and oversee all operations. This project was developed using Java Servlets, JSP, HTML, CSS, JavaScript, Bootstrap, and MailSender.

## Features
- **User Roles**: 
  - **Tester**: Identify and report bugs.
  - **Developer**: Assigned bugs to fix.
  - **In-charge**: Supervise bug resolution processes.
  - **Admin**: Manage users, view reports, and oversee the entire system.

- **Bug Reporting**: Testers can report bugs with detailed descriptions, screenshots, and severity levels.

- **Bug Assignment**: Bugs are assigned to developers based on their expertise and availability.

- **Supervision**: In-charges can monitor the progress of bug fixes, ensuring timely resolution.

- **Admin Controls**: Admins have full control over the system, including user management, viewing bug reports, and assigning roles.

- **Email Notifications**: Automatic email notifications are sent to relevant users at various stages of the bug resolution process.

## Technology Stack
- **Frontend**: 
  - HTML
  - CSS
  - Bootstrap
  - JavaScript

- **Backend**:
  - Java Servlets
  - JSP

- **Utilities**:
  - MailSender for email notifications

## Setup and Installation
To set up the Bug Tracker on your local machine:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/bug-tracker-system.git
   ```

2. **Import the project**:
   - Open your IDE (e.g., Eclipse) and import the project as a Maven/Java project.

3. **Database Setup**:
   - Create a MySQL database and import the provided SQL script to set up the necessary tables.
   - Update the database connection settings in the `db.properties` file.

4. **Deploy the application**:
   - Deploy the project on Apache Tomcat or any other compatible Java server.

5. **Run the application**:
   - Access the application via `http://localhost:8080/bug-tracker-system`.

## Usage
- **Admin**: Log in using admin credentials to create user accounts, assign roles, and oversee the system.
- **Tester**: Log in to report new bugs.
- **Developer**: View and fix assigned bugs.
- **In-charge**: Supervise and track the status of bug resolution.

## Contributing
Contributions are welcome! Please fork this repository and submit a pull request for review.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any questions or inquiries, please reach out to (mailto:jdeeran2004@gmail.com).
