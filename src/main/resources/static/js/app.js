angular
    .module("routingApp", ["ngRoute", "ngSanitize"])
    .config([
        "$routeProvider",
        function ($routeProvider) {
            $routeProvider
                .when("/", {
                    templateUrl: "/landing.html"
                })
                .when("/login", {
                    templateUrl: "/login.html",
                    controller: "LoginCtrl",
                    controllerAs: "ctrlLogin",
                })
                .when("/admin/user/register", {
                    templateUrl: "/view/admin/users/user_register.html",
                    controller: "UserRegisterCtrl",
                    controllerAs: "ctrlUserRegister",
                })
                .when("/admin/user", {
                    templateUrl: "/view/admin/users/user_list.html",
                    controller: "UserListCtrl",
                    controllerAs: "ctrlUserList",
                })
                .when("/receptionist/client/register", {
                    templateUrl: "/view/receptionist/client_register.html",
                    controller: "ClientRegisterCtrl",
                    controllerAs: "ctrlClientRegister",
                })
                .when("/receptionist/client/", {
                    templateUrl: "/view/receptionist/client_list.html",
                    controller: "ClientListCtrl",
                    controllerAs: "ctrlClientList",
                })
                .when("/receptionist/client/pets/:id", {
                    templateUrl: "/view/receptionist/pet_list.html",
                    controller: "PetListCtrl",
                    controllerAs: "ctrlPetList",
                })
        }
    ]).constant('APP_URL', {
        url: "http://localhost:8080"
    })
    .value("SESSION", {
        token: localStorage.getItem("token"),
    }).run(function ($rootScope, $location) {
        $rootScope.$on(
            "$routeChangeError",
            function (event, current, previous, rejection) {
                if (rejection === "Not Authenticated") {
                    $location.path("/login");
                } else if (rejection === "Session") {
                    $location.path("/");
                }
            }
        );
    });