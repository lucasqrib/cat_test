## 👋 Hello! Welcome to the Cats Test

### Structure

The project is basically divided in 3 packages:

- [data](https://github.com/lucasqrib/cat_test/blob/master/README.md#the-data-package) package: Is responsible for all the data logic (repository, mappers, data transfer objects and datasources), there's none Android code in here

- [domain](https://github.com/lucasqrib/cat_test/blob/master/README.md#the-domain-package) package: Where all the businnes logic (despite in this app case, is almost none) and model objects are, there's none Android code in here too

- [platform](https://github.com/lucasqrib/cat_test/blob/master/README.md#the-platform-package) package: Where all the platform (Android) specific code are, like network, presentation and dependency injection code.

---

### The Data Package
#### Datasource
In this package we have the interfaces which our repositories should interact to get data from.
There's no implementation because the datasource implementation is platform bounded, and should by implemnted decoupled of the data logic

#### DTO
In here, we mapped the data transfer object that we expect.

#### Mapper
Classes responsible for map the data transfer object to the domain object, creating an decoupling layer between the data and domain models.

#### Repository
The repository implementation class is responsible for get the data from the datasource and transform to the platform agnostic result, decoupling and transforming the datasource erros in domain errors.
I enforced the use of an IO dispatcher (which comes from the Dependency Injection), to prevent any call in UI thread, the dispatcher is received on the class constructor to enable easier testing

---

### The Domain Package
#### Model
Classes that represent our cats, in here, we asbtract all data transfer object complications and missbehaviors, like working with 1 and 0 instead of booleans like it's in the api response

#### Repository
Our contract to get the data, should not contain any implementation

#### Use Case
Classes and contracts that should represent one use case, in this app, because it's quite simple, there's not much of logic in here, just to decouple the platform call from the repository and be prepared to manipulate the data if needed.

---

### The Platform package
#### DI (Dependency Injection)
Basically the Dagger/Hilt modules to provide the dependency injection for the whole App

#### Network
The implementation of the OkHttpClient is in here, where I configured two interceptors, one for logging in Debug mode, and other to add the x-api-key header in all the requests.
The credentials and Connection URL are configured in the app build.gradle to provide easier enviroment changes.
In here there is the Retrofit interface, where we configure our API's calls

#### Main Activity and View Model
The Activity basically just start the Navigation flow and setup the App Bar.
The View Model have the list of retrieved cats from the repository and the tracking of the page.
I exposed the data to the `CatListFragment` using LiveData objects and serve the selected Cat to the `CatDetailFragment`.
Using an shared View Model, we prevent some complexity, because we dont need to get the object from repository.

#### Detail package
Basically I have the `CatDetailFragment`, which receives a Cat Id, retrieve the Cat object from View Model and populate the screen.

#### List package
In here there are the `CatFragmentList`, which have the logic to create de `RecyclerView` and call the ViewModel to load more cats, and the `RecyclerView` related classes:
- `CatListRecyclerAdapter`: Is the adapter responsible for show the cat list, there is some logic to change between an cat list item and a loading item, where we load more items to present.
- `CatListItemViewHolder` and `CatListItemLoadingViewHolder`: Both extends from `BaseCatViewHolder`, the first one bind the Cat object to the view layout, and the latter shows an circular loading layout.

---

### Resources
In the resources there's variations for two locations and dark mode

