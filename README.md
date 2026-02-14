# 📘 Dibimbing LMS – UI Automation (Employee Training)

UI Automation Testing project for Dibimbing LMS – Employee Training using Java, Selenium WebDriver, TestNG, and Gradle, implemented with Page Object Model (POM) and Extent Report.

---

## 🚀 Tech Stack

Language: Java 17  
UI Automation: Selenium WebDriver  
Test Framework: TestNG  
Design Pattern: Page Object Model (POM)  
Build Tool: Gradle  
Reporting:
- TestNG HTML Report
- Extent Report (HTML)  
  Cross Browser: Chrome, Firefox, Safari

---

## 🧪 Test Coverage

👤 Employee Module
- Add Employee (Positive)
- Failed add employee when email invalid
- Failed add employee when required field empty
- Failed add employee when employee ID duplicate
- Edit Employee
- Delete Employee
- Search Employee

🏢 Division Module
- Add Division
- Failed add division when required field empty
- Failed add division when duplicate name
- Edit Division
- Delete Division

🎓 Training Module
- Add Training
- Edit Training
- Search Training
- Pagination verification

📘 Content Module
- Add Article
- Add Video
- Add Test
- Edit Content
- Delete Content
- Failed when:
    - Duration invalid
    - Required field empty

📌 Assign Module
- Assign employee to training
- Edit assignment deadline
- Delete assignment
- Failed when:
    - Deadline < Start date
    - Duplicate assignment

---

## 📊 Automation Coverage

Total Test Case: 61  
Automated (UI): 43 (70%)  
Focus Area: High priority regression scenarios

---

## ▶️ How to Run Test (Local)

Run all test:

```bash
./gradlew clean test
```

Or specific suite:

```bash
./gradlew clean test -Dsuite=regression-chrome.xml
./gradlew clean test -Dsuite=regression-firefox.xml
./gradlew clean test -Dsuite=regression-safari.xml
```

---

## 🌐 Cross Browser Execution

Browser dapat dikonfigurasi melalui parameter pada BaseTest atau testng.xml:

Supported:
- Chrome
- Firefox
- Safari

---

## 🔔 Slack Notification (CI/CD)

Automation sudah terintegrasi dengan Slack untuk mengirim hasil eksekusi setiap pipeline berjalan.

Informasi yang dikirim:

- Status (PASSED / FAILED)
- Total Tests
- Passed / Failed / Skipped
- Pass Rate (%)
- Branch
- Triggered By (GitHub Actor)

Notifikasi dikirim melalui GitHub Actions menggunakan Slack Webhook setelah proses testing selesai.
