angular.module('cwmd').component('workingArea', {
    templateUrl: 'app/js/user/working-area/workingArea.html',
    controller: function (DRDocumentSrv, RNDocumentSrv, FlowSrv, DepartmentSrv, $window) {
        var $ctrl = this;

        $ctrl.rnDownloadLink = "http://localhost:8080/rn/RN_document_sample";
        $ctrl.canStartFlow = false;
        $ctrl.documentsForFlow = null;
        $ctrl.departmentsForFlow = null;

        $ctrl.$onInit = function () {
            DRDocumentSrv.getDocumentsByUser()
                .then(function (response) {
                    $ctrl.drDocuments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
            RNDocumentSrv.getDocumentsByUser()
                .then(function (response) {
                    $ctrl.rnDocuments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
            DepartmentSrv.getDepartments()
                .then(function (reponse) {
                    $ctrl.departments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.addDocumentToFlow = function (id) {
            $ctrl.documentsForFlow.push(id);
            $ctrl.canStartFlow = true;
        };

        $ctrl.addDepartmentsToFlow = function (id) {
            $ctrl.departmentsForFlow.push(id);
            $ctrl.canStartFlow = true;
        };

        $ctrl.startFlow = function () {
            if(!$ctrl.documentsForFlow){
                alert("There are no documents available to start the flow!");
                return;
            }
            if(!$ctrl.departmentsForFlow){
                alert("There are no departments available to start the flow!");
                return;
            }

            FlowSrv.startFlow($trl.documentsForFlow, $ctrl.departmentsForFlow)
                .then(function (reponse) {
                    alert("Flow with id" + response + "started!");
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});