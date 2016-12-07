angular.module('cwmd').service('DRDocumentSrv', function (Restangular, FileSaver, Blob) {
    var service = this;

    service.getDocuments = function () {
        return Restangular.all('document').getList();
    };

    service.getFirstPart = function () {
        Restangular.all('document').customGET('downloadFirst', {}, {
            'Accept': 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'})
            .then(function (data) {
                debugger;
                var file = new Blob([data], {
                    type: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=utf-8'
                });

                FileSaver.saveAs(file, 'DR-firstpart-sample.docx', false);
            })
            .catch(function (response) {
                console.log(response);
            })
    };

    service.uploadFirstPart = function (file) {
        var fd = new FormData();
        fd.append('file', file);
        return Restangular.one('/document/upload').withHttpConfig({transformRequest: angular.identity})
            .customPOST(fd, '', undefined, {'Content-Type': undefined})
    };

    service.uploadSecondPart = function (file, documentId) {
        var fd = new FormData();
        fd.append('file', file);
        return Restangular.one('/document/upload').withHttpConfig({transformRequest: angular.identity})
            .customPOST(fd, '', undefined, {'Content-Type': undefined})
    };

    service.getSecondPart = function () {
        Restangular.all('document').customGET('downloadSecond', {}, {
            'Accept': 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'})
            .then(function (data) {
                debugger;
                var file = new Blob([data], {
                    type: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=utf-8'
                });

                FileSaver.saveAs(file, 'DR-secondpart-sample.docx', false);
            })
            .catch(function (response) {
                console.log(response);
            })
    };
});