# Super Stars
This project provides services to fetch popular data from different third party providers

## Services
* [Super GitHub](./super-github): Get popular repositories from Github.

### Run Book
 - navigate to the directory `super-github` of the project
 - run the command from your terminal `gradle jibDockerBuild && docker-compose up`

### Application Configurations
- The application configurations are in the `application.yml` file in the `src/main/resources` directory.
- The configurations are as follows:
  ```sh
    github.api.endpointUrl  // endpoint to fetch repositories data from github
    github.api.sinceDate=   // date to fetch repositories data from github
    github.api.sortType=   // sort type to fetch repositories data from github
    github.api.orderType=  // order type to fetch repositories data from github
    github.api.listLimit= // limit to fetch repositories data from github
    github.repositories.filter-type= // filter type to filter repositories data from github (currently only `language` is supported)
  ```
  
