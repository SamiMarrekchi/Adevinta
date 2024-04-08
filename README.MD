# Technical-Test

## Architecture Overview:

The architecture is inspired from the Clean Architecture based on the new Android Architecture Components, here is how
the project implements it :
[Android Clean architecture](https://developer.android.com/topic/architecture?hl=fr)

## **Layers:**

### 1-Presentation Layer :

The role of the UI is to display the application's data on the screen and to act as the main point of user interaction.
Whenever data changes, whether as a result of user interaction (such as pressing a button) or external input (such as a
network response).

The UI needs to be updated to reflect these changes. In Our case:

We apply the MVVM

* For the interface we use Jetpack Compose for interface design
* The viewModel notify the interface when data updated using **StateFlow**
* ViewModel inject usecases from Domain Layer

### 2-Domain Layer :

The domain layer is responsible for encapsulating business logic, reused by multiple ViewModels.
In This layer , we find models, Interface Repository, and UseCases

* UseCases inject Repository to communicate with the data layer

### 2-Data Layer :

The data layer contains application data and business logic. The business logic is what gives value to your app—it's
made of real-world business rules that determine how application data must be created, stored, and changed.

* We have RepositoriesImpl which use DataSource(Remote/Local)
    * LocalDataSources: Manage local Data and return Domain Model
    * RemoteDataSource: Manage Remote Data and return Domain Model
* Daos(@Dao) : Interfaces includes methods that offer abstract access to your app's database
* ApiService: Interfaces includes methods to call remote apis (**@GET("technical-test.json")**)

Libraries Used
* Compose
* Hilt
* Retrofit
* Coil
* Coroutines
* Room
* Mockito
* ...

## Tests :

Layers

- [x] DataSources
    - [x] Remote
    - [x] Local (Room)
- [x] Domain
- [x] ViewModel
- [ ] User Interface

## What Can be Improved when the project is envisioned to grow :

* Seperate each Layer in a module Domain / Data / Presentation