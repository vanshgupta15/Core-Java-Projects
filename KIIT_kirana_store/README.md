## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

SimpleShoppingCenter/
│
├── src/
│   ├── config/               # Database configuration & constants
│   │   └── DBConnection.java
│
│   ├── model/                # POJO classes (Data models)
│   │   ├── Product.java
│   │   ├── User.java
│   │   ├── Order.java
│   │   └── Report.java
│
│   ├── dao/                  # Data Access Objects (Database logic)
│   │   ├── ProductDAO.java
│   │   ├── UserDAO.java
│   │   ├── OrderDAO.java
│   │   └── ReportDAO.java
│
│   ├── service/              # Business logic layer
│   │   ├── ProductService.java
│   │   ├── AuthService.java
│   │   ├── OrderService.java
│   │   └── ReportService.java
│
│   ├── ui/                   # GUI components (Swing)
│   │   ├── LoginFrame.java
│   │   ├── ShopkeeperDashboard.java
│   │   ├── StaffDashboard.java
│   │   ├── CustomerDashboard.java
│   │   ├── ProductManagementPanel.java
│   │   └── OrderManagementPanel.java
│
│   ├── util/                 # Utility classes
│   │   ├── InputValidator.java
│   │   └── SessionManager.java
│
│   └── Main.java             # Application entry point
│
├── lib/                      # External libraries (e.g., MySQL JDBC jar)
│
├── resources/                # Images, properties files, icons, etc.
│
├── sql/                      # DB scripts (create tables, insert data)
│   └── schema.sql
│
├── README.md
└── build.gradle / pom.xml    # If using Gradle or Maven (optional)
