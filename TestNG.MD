**🛠️ TestNG Framework 📜**

## 🔰 Introduction
TestNG (Test Next Generation) is a 🧪 framework inspired by JUnit 🏛️ and NUnit, designed for 🖥️ Java applications ☕. It provides 🔝 features such as 📌 annotations, 🔗 dependencies, 🔄 grouping, ⚡ parallel execution, and more. TestNG is widely used for 🏗️ unit, 🔗 integration, and 🎯 functional testing.

## 📌 Annotations
TestNG uses annotations to define 🏃 test behavior and execution flow. Below are the key annotations:

- `@Test` 🏷️: Marks a method as a ✅ test case.
- `@BeforeSuite` ⏳: Runs before all 🧪 in the 🏠 suite.
- `@AfterSuite` 🎬: Runs after all 🧪 in the 🏠 suite.
- `@BeforeTest` 🔧: Runs before any 🧪 method in a 📦 class.
- `@AfterTest` 🗑️: Runs after all 🧪 methods in a 📦 class.
- `@BeforeClass` 🚀: Runs before the first 🧪 method in the current 📦.
- `@AfterClass` 🛑: Runs after all 🧪 methods in the current 📦.
- `@BeforeMethod` ⏩: Runs before each 🧪 method.
- `@AfterMethod` ⏪: Runs after each 🧪 method.
- `@DataProvider` 📤: Supplies 📝 test data to 🧪 methods.
- `@Parameters` 🛠️: Passes values to test methods from an XML file.
- `@Factory` 🏭: Generates dynamic test cases at runtime.
- `@Listeners` 🎧: Attaches event listeners to track test execution.
- `@Ignore` 🚫: Skips a test case from execution.
- `@Timeout` ⏲️: Specifies the maximum time a test method should take to execute.

## 🏗️ Test Methods, Classes & Groups

### 🛠️ Test Methods
Test methods are the core of TestNG, defined using the `@Test` annotation. Example:
```java
import org.testng.annotations.Test;

public class SampleTest {
    @Test
    public void testMethod() {
        System.out.println("🚀 Executing test method");
    }
}
```

### 📦 Test Classes
A test 📦 contains multiple 🧪 methods and annotations. Example:
```java
import org.testng.annotations.*;

public class TestClass {
    @BeforeClass
    public void setup() {
        System.out.println("🛠️ Setup before class execution");
    }

    @Test
    public void testMethodOne() {
        System.out.println("✅ Test Method One Executed");
    }

    @AfterClass
    public void teardown() {
        System.out.println("🗑️ Cleanup after class execution");
    }
}
```

### 🎯 Test Groups
Test groups allow categorization of 🧪 for selective execution. Example:
```java
import org.testng.annotations.*;

public class GroupTest {
    @Test(groups = {"🔥smoke"})
    public void smokeTest() {
        System.out.println("🔥 Executing smoke test");
    }

    @Test(groups = {"🌀regression"})
    public void regressionTest() {
        System.out.println("🌀 Executing regression test");
    }
}
```
To run specific groups, define them in the `testng.xml` file:
```xml
<suite name="🧪 TestSuite">
    <test name="🔥 SmokeTests">
        <groups>
            <run>
                <include name="🔥smoke"/>
            </run>
        </groups>
        <classes>
            <class name="GroupTest"/>
        </classes>
    </test>
</suite>
```

## 🔗 Dependencies
TestNG allows defining dependencies between 🧪 methods using the `dependsOnMethods` attribute.
```java
import org.testng.annotations.Test;

public class DependencyTest {
    @Test
    public void login() {
        System.out.println("🔑 Login Test");
    }

    @Test(dependsOnMethods = {"login"})
    public void dashboardTest() {
        System.out.println("📊 Dashboard Test");
    }
}
```
Here, `📊 dashboardTest` will execute only if `🔑 login` passes.

## 🚀 Running TestNG

### 🏗️ Running from Command Line
1. 🏗️ Compile the 🧪 classes:
   ```sh
   javac -cp ".;testng.jar" SampleTest.java
   ```
2. 🎬 Run the 🧪 using:
   ```sh
   java -cp ".;testng.jar" org.testng.TestNG testng.xml
   ```

### 🖥️ Running from Eclipse
1. 🛠️ Install the TestNG plugin.
2. ▶️ Right-click the 🧪 class and select `Run As > TestNG Test`.

### 📦 Running with Maven
Define TestNG dependency in `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
Run the 🧪 with:
```sh
mvn test
```

This 📜 provides an overview of TestNG, its 📌 annotations, 🏗️ test structures, 🔗 dependencies, and execution methods. 🚀

