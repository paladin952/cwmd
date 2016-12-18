angular.module('cwmd').service('RNDocumentSrv', function (Restangular, FileSaver, Blob) {
    var service = this;

    service.getDocuments = function () {
        return Restangular.all('rn').getList();
    };

    service.upload = function (file) {
        var fd = new FormData();
        fd.append('file', file);
        return Restangular.one('/rn/upload').withHttpConfig({transformRequest: angular.identity})
            .customPOST(fd, '', undefined, {'Content-Type': undefined})
    };
});