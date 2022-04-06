angular.module('app', []).controller('indexController', function ($scope, $http) {

    ws = new WebSocket("ws://localhost:8189/generation");

    ws.onopen = function(){
        action('open connection');
    };

    ws.onmessage = function(ev){
        action(ev.data);
    }

    function action(message){
        var mess = document.getElementById("mess");
        mess.textContent = message;
    }


    $scope.fillTable = function () {
        $http.get('http://localhost:8189/api/seq')
            .then(function (response) {
                $scope.sequences = response.data;
                console.log($scope.sequences);
            });
    }

    $scope.generateBtn = function() {
        $http.get('http://localhost:8189/api/seq/generate')
            .then(function (response) {
                   $scope.sequences = response.data;
                   console.log($scope.sequences);
             });
    }

    var toggled = false;
    var handle;
    $scope.autoBtn = function() {
        if(!toggled){
            toggled = true;
            document.getElementById("autoBtn").style.color="red"
            handle = setInterval(() => {
                        $http.get('http://localhost:8189/api/seq/generate')
                            .then(function (response) {
                                $scope.sequences = response.data;
                                console.log($scope.sequences);
                            });
                        }, 10000);
            return;
        }
        if(toggled){
            clearInterval(handle);
            document.getElementById("autoBtn").style.color="grey"
            toggled = false;
            return;
        }

    }

    $scope.addSeq = function(length) {
        if ($scope.length < 10){
            alert("Длина от 10 до 100");
        } else if ($scope.length > 100){
            alert("Длина от 10 до 100");
        }
        else {
            ws.send($scope.length);
            $http.post('http://localhost:8189/api/seq/', $scope.length)
                .then(function (response) {
                    $scope.fillTable();
                    console.log($scope.sequences);
                });
        }
    }
    $scope.fillTable();
});