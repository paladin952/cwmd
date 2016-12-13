angular.module('cwmd').component('workingArea', {
    templateUrl: 'app/js/user/working-area/workingArea.html',
    controller: function (DRDocumentSrv, $state) {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            DRDocumentSrv.getDocuments()
                .then(function (response) {
                    $ctrl.documents = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.addDocument = function () {
            $state.transitionTo("home.addDr");
        };

        $ctrl.downloadDRSamples = function () {
            $state.transitionTo("home.downloadDr")
        }
    }
});