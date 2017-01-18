angular.module('cwmd').component('workingArea', {
    templateUrl: 'app/js/user/working-area/workingArea.html',
    controller: function (DRDocumentSrv, RNDocumentSrv, FlowSrv, DepartmentSrv, $state) {
        var $ctrl = this;

        $ctrl.rnDownloadLink = "http://localhost:8080/rn/RN_document_sample";
        $ctrl.canStartFlow = false;
        $ctrl.canAddDocuments = false;
        $ctrl.documentsForFlow = [];
        $ctrl.departmentsForFlow = [];

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
                .then(function (response) {
                    $ctrl.departments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.getDepartments = function (query) {
            return $ctrl.departments;
        };

        $ctrl.addDocumentToFlow = function (id) {
            $ctrl.documentsForFlow.push(id);
            $ctrl.canStartFlow = true
        };

        $ctrl.addDepartmentsToFlow = function (id) {
            $ctrl.departmentsForFlow.push(id);
            $ctrl.canStartFlow = true
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

            var departmentIds = _.map($ctrl.departmentsForFlow, 'id');

            FlowSrv.startFlow($ctrl.documentsForFlow, departmentIds)
                .then(function (response) {
                    // alert("Flow with id" + response + "started!");
                    $state.go("openTasks");
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});