angular.module("routingApp").controller("LoginCtrl", [
    "$rootScope",
    "$scope",
    "$http",
    "APP_URL",
    "SESSION",
    "$routeParams",
    "$window",
    function ($rootScope, $scope, $http, APP_URL, SESSION, $routeParams, $window) {
        const notyf = new Notyf({
            duration: 2500,
            position: {
                x: "right",
                y: "top",
            },
        });

        $rootScope.utils = {
            role: null,
        };

        $scope.initial = function () {
            if (localStorage.getItem("token")) {
                $rootScope.currentSession = true;
                $rootScope.role = localStorage.getItem("role");
                $rootScope.utils.role = $scope.decodeToken(localStorage.getItem("token")).role;
            } else {
                $rootScope.currentSession = false;
                $rootScope.utils = {
                    img: null,
                    id: null,
                    role: null
                };
            }
        }

        $scope.redirectHandler = () => {
            if (localStorage.getItem("token")) {
                if (localStorage.getItem ("role") == "ROLE_ADMINISTRADOR") {
                    $window.location.href = "/#!/admin/user"
                } else if (localStorage.getItem("role") == "ROLE_RECEPCIONISTA") {
                    $window.location.href = "/#!/receptionist";
                } else if (localStorage.getItem("role") == "ROLE_DOCTOR" || localStorage.getItem("role") == "ROLE_ESTILISTA") {
                    $window.location.href = "/#!/my-schedules";
                } else {
                    $window.location.href = "/#!/login";
                }
            } else {
                $window.location.href = "/#!/login";
            }
        }

        $scope.redirectHandler2 = (target) => {
            if (localStorage.getItem("token")) {
                if (localStorage.getItem("role") != target && localStorage.getItem("role") != "ROLE_ESTILISTA") {
                    $scope.redirectHandler();
                }
            } else {
                $window.location.href = "/#!/login";
            }
        }

        (() => {
            "use strict";
            const forms = document.querySelectorAll(".needs-validation");
            Array.prototype.slice.call(forms).forEach((form) => {
                form.addEventListener(
                    "submit",
                    (event) => {
                        if(!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                            notyf.error("Llena los campos necesarios");
                        } else {
                            this.beforeLogin();
                        }form.classList.add("was-validated");
                    }, false)
            });
        })();

        $scope.getToken = function () {
            $rootScope.role = localStorage.getItem("role");
            if (localStorage.getItem("token")) {
                return true;
            } else {
                return false;
            }
        }

        $rootScope.currentSession = false;
        $rootScope.role = "";

        this.beforeLogin = () => {
            return $http({
                method: "POST",
                url: `${APP_URL.url}/users/session`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
                data: $scope.user,
            }).then((res) => {
                this.login();
            }).catch((err) => {
                console.log(err);
            });
        }

        $scope.logout = () => {
            $rootScope.currentSession = false;
            $window.localStorage.clear();
            $window.location.href = "#!/login";
            $scope.initial;
        }

        (() => {
            "use strict";
            const forms = document.querySelectorAll(".needs-validation");
            Array.prototype.slice.call(forms).forEach((form) => {
                form.addEventListener(
                    "submit",
                    (event) => {
                        if (!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                        } else {
                            this.beforeLogin();
                        }
                        form.classList.add("was-validated");
                    }
                );
            });
        });

        $scope.user = {
            username: null,
            password: null,
        };

        this.login = () => {
            return $http({
                method: "POST",
                url: `${APP_URL.url}/users/login`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
                data: $scope.user,
            }).then((res) => {
                if (res.data) {
                    $window.localStorage.setItem("token", res.data.jwtToken);
                    let name = $scope.decodeToken(res.data.jwtToken);
                    $window.localStorage.setItem("role", name.role);
                    $rootScope.currentSession = $scope.getToken();
                    notyf.success("¡Bienvenido/a!");
                    $scope.initial();
                    if (name.role == "ROLE_ADMINISTRADOR") {
                        $window.location.href = "/#!/admin/user";
                    } else if (name.role == "ROLE_RECEPCIONISTA") {
                        $window.location.href = "/#!/receptionist";
                    } else {
                        $window.location.href = "/#!/my-schedules";
                    }
                } else {
                    notyf.error("Usuario y/o Contraseña Incorrecto");
                }
            }).catch((err) => {
                console.log(err);
            });
        }

        $scope.decodeToken = (token) => {
            var base64Url = token.split(".")[1];
            var base64 = base64Url.replace("-", "+").replace("_", "/");
            return JSON.parse($window.atob(base64));
        }
    }
]);