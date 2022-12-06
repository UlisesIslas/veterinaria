angular.module("routingApp").controller("UserRegisterCtrl", [
    "$rootScope",
    "$scope",
    "$http",
    "APP_URL",
    "$location",
    "$routeParams",
    "$filter",
    "$window",
    function ($rootScope, $scope, $http, APP_URL, $location, $routeParams, $filter, $window) {
        $scope.token = "Bearer " + localStorage.getItem("token");
        const notyf = new Notyf({
            duration: 2500,
            position: {
                x: "right",
                y: "top",
            },
        });

        $scope.user = {
            username: null,
            password: null,
            password2: null,
            name: null,
            lastname: null,
            surname: null,
            enabled: true,
            image: null,
            phone: null,
            gender: null,
            roles: null,
            salary: null,
        };

        this.getRoles = () => {
            return $http({
                method: "GET",
                url: `${APP_URL.url}/users/roles`,
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
            }).then((res) => {
                $scope.listRoles = res.data;
            })
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
                            notyf.error("Llena los campos necesarios");
                        } else {
                            this.save();
                        } form.classList.add("was-validated");
                    }, false)
            });
        })();

        this.save = () => {
            if ($scope.user.password != null && $scope.user.password2 != null) {
                if ($scope.user.password == $scope.user.password2) {
                    return $http({
                        method: "POST",
                        url: `${APP_URL.url}/users/save`,
                        headers: {
                            "Content-Type": "application/json",
                            Accept: "application/json",
                            authorization: $scope.token,
                        },
                        data: $scope.user
                    }).then((res) => {
                        $window.location.href = "#!/admin/user"
                        notyf.success("Usuario registrado");
                    })
                } else {
                    notyf.error("Las contraseñas no son iguales");
                }
            } else {
                notyf.error("Ingresa una contraseña");
            }
        }

        $(document).ready(function () {
            $('#gender').select2({
                placeholder: "SELECCIONAR SEXO...",
                allowClear: false
            });
            $('#role').select2({
                placeholder: "SELECCIONAR ROL...",
                allowClear: false
            });
        });
    }
]);