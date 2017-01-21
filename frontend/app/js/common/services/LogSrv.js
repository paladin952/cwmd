angular.module('cwmd').service('LogSrv', function (Restangular) {
    var service = this;

    service.getAllLogs = function () {
        return Restangular.all('log').getList();
    };

    service.getAllLogsWithFilter = function (filter) {
        var path = "log/all/filter";

        return Restangular.all(path).getList(filter);
    };

    service.getAllLogsWithTimestamp = function (from, to) {
        var path = "log/all/timestamps";
        var timestamp = {
            from: from,
            to: to
        };
        return Restangular.all(path).getList(timestamp);
    };

    service.getAllLogsWithAllFilters = function (filter, from, to) {
        var path = "log/all/all_filters";
        var timestamp = {
            from: from,
            to: to
        };
        var parameters = {
            filer: filter,
            timestamp: timestamp
        };

        return Restangular.all('log').getList(parameters);
    };

    service.getAllDebugLogs = function () {
        var path = "log/debug";

        return Restangular.all(path).getList();
    };

    service.getAllDebugLogsWithFilter = function (filter) {
        var path = "log/debug/filter";

        return Restangular.all(path).getList(filter);
    };

    service.getAllDebugLogsWithTimestamp = function (from, to) {
        var path = "log/debug/timestamps";
        var timestamp = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(timestamp);
    };

    service.getAllDebugLogsWithAllFilters = function (filter, from, to) {
        var path = "log/debug/all_filters";
        var timestamp = {
            from: from,
            to: to
        };
        var parameters = {
            filer: filter,
            timestamp: timestamp
        };

        return Restangular.all('log').getList(parameters);
    };

    service.getAllInfoLogs = function () {
        var path = "log/info";

        return Restangular.all(path).getList();
    };

    service.getAllInfoLogsWithFilter = function (filter) {
        var path = "log/info/filter";

        return Restangular.all(path).getList(filter);
    };

    service.getAllInfoLogsWithTimestamp = function (from, to) {
        var path = "log/info/timestamps";
        var timestamp = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(timestamp);
    };

    service.getAllInfoLogsWithAllFilters = function (filter, from, to) {
        var path = "log/info/all_filters";
        var timestamp = {
            from: from,
            to: to
        };
        var parameters = {
            filer: filter,
            timestamp: timestamp
        };

        return Restangular.all('log').getList(parameters);
    };

    service.getAllWarnLogs = function () {
        var path = "log/warn";

        return Restangular.all(path).getList();
    };

    service.getAllWarnLogsWithFilter = function (filter) {
        var path = "log/warn/filter";

        return Restangular.all(path).getList(filter);
    };

    service.getAllWarnLogsWithTimestamp = function (from, to) {
        var path = "log/warn/timestamps";
        var timestamp = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(timestamp);
    };

    service.getAllWarnLogsWithAllFilters = function (filter, from, to) {
        var path = "log/warn/all_filters";
        var timestamp = {
            from: from,
            to: to
        };
        var parameters = {
            filer: filter,
            timestamp: timestamp
        };

        return Restangular.all('log').getList(parameters);
    };

    service.getAllErrorLogs = function () {
        var path = "log/error";

        return Restangular.all(path).getList();
    };

    service.getAllErrorLogsWithFilter = function (filter) {
        var path = "log/error/filter";

        return Restangular.all(path).getList(filter);
    };

    service.getAllErrorLogsWithTimestamp = function (from, to) {
        var path = "log/error/timestamps";
        var timestamp = {
            from: from,
            to: to
        };

        return Restangular.all(path).getList(timestamp);
    };

    service.getAllErrorLogsWithAllFilters = function (filter, from, to) {
        var path = "log/error/all_filters";
        var timestamp = {
            from: from,
            to: to
        };
        var parameters = {
            filer: filter,
            timestamp: timestamp
        };

        return Restangular.all('log').getList(parameters);
    };

});