angular.module('cwmd').component('addRn', {
    templateUrl: 'app/js/user/working-area/addRn/addRn.html',
    controller: function ($scope, $location, RNDocumentSrv) {
        var $ctrl = this;

        $ctrl.upload = function () {
            if ($scope.document !== undefined) {
                RNDocumentSrv.upload($scope.document)
                    .then(function (response) {
                        $location.path("/workingArea");
                    })
                    .catch(function (response) {
                        console.log(response);
                    });
            }
        };
    }
});