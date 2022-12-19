# SpringWebApplication_JobBoardProject
# Job Board Web Application
What is a **job board ?**. Well this is what I got from one minute google search "_an online service that employers use to advertise jobs_". Even better, "_job board is a website used 
by employers to advertise their job vacancies to job seekers. Job seekers can use job boards to search for new job opportunities in their area and profession. 
Some well-known job board sites are Indeed, Glassdoor, and Careerjet to name a few._"

While I am not trying to create the next Glassdoor or Indeed or Job.cz (I mean I would
prefer to create something much greater and better), this project is a web application where employers/organizations can register as a users/employers and post job vacancies, job seekers can view, search and of course, apply and submit their resumes to posted/advertised job vanacies. Registered employers can not only view their posted vacancies but they can also review responses to such vacancies and download submitted resumes.


## Technologies

This project is based almost entired in Java and Java-based frameworks such as:
1. Core Java
2. Core Spring (IoC container)
3. Spring Boot (to simplify Spring configuaration, bean definition/registry/e.t.c, servlet engine)
4. Spring MVC (the core web application backing framework.)
5. Spring Security (mainly for registeration, authentication and authorization of employers/organizations)
6. Spring Data (provides APIs for seamlessly working with Hibernate which provides implementation of Java Persistence API (JPA)
7. Thymeleaf (a Java server-side template engine. JSP was initially used, however thymeleaf was later favord due to flexibility. You can see my other project on JSP.
8. Boostrap (for CSS classes in presentation layer)
9. MySQL 

## How To Use

No installation is required (as long as you use Maven or Gradle as your build tool) to use or test this application. However, as shown in the `application.properties` configuration file. You need to provided your database information that follows the schema used by the Models. For reproducibility and clarity, I am providing the SQL statements that was used to the create the database tables that backs the  `Models` used in the application. 
- Employer Model (Note that the `EmployerRegistrationForm` model is just a form backing model that is not persisted to a database but instead used to create the Employer model). The Employer model corresponds to the recognized data source by Spring Security. 
```SQL
create table if not exists Users (
        id int auto_increment primary key,
        username varchar(50) not null,
        password varchar(1000) not null,
        organizationName varchar(50) not null,
        industrialSectors varchar(50),
        emailAddress varchar(50) not null,
        street varchar(50) not null,
        city varchar(20) not null,
        zip varchar(10) not null,
        accountCreationDate datetime,
        phone varchar(13) not null
)
```

- Jobs (respresents a job vacancies that a registered employer can create and post to the job board)
```SQL
create table if not exists Jobs(
        jobId int auto_increment primary key,
        title varchar(100) not null,
        jobdescription text not null,
        requirededucation varchar(50),
        requiredlanguage varchar(50),
        industrialSector varchar(50) not null,
        location varchar(50),
        salaryband varchar(50),
        experienceLevel varchar (50),
        postdate datetime not null,
        employerid int,
        employername varchar(50),
        foreign key(employerid) references users(id)
)
```

- JobResponse Model (respresents the application submitted to a job vacancies including the resume file)
```SQL
create table if not exists JobResponse(
       jobResponseId int auto_increment primary key,
       firstName varchar (50),
       lastName varchar (50),
       fileName varchar(500),
       emailAddress varchar(50),
       resumeFile mediumblob,
       responseDate datetime,
       jobId int,
       foreign key(jobId) references jobs(jobId) 
)
```

## Deployment

The application can be run locally for example along with data tables on MySQL workbench. It can also be easily deployed to Azure Spring Apps. There is a live deployment of a version of the application at https://shola-jobboard.azuremicroservices.io/

## Licencse

What License?....This absolutely free small project. If by any cahnce you find something useful, use it, share it, whatever....Enjoy! Most likely, you will find a bug or some code stink/smell or better way of doing things, I had appreciate your feedback on improvement.








