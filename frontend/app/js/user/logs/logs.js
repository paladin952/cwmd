angular.module('cwmd').component('logs', {
    templateUrl: '/app/js/user/logs/logs.html',
    controller: function (LogSrv) {

        var $ctrl = this;
        $ctrl.logs = [];
        $ctrl.logTypes = ['All', 'Debug', 'Info', 'Warn', 'Error'];
        $ctrl.filterTypes = ['Filter', 'Timestamps'];
        $ctrl.filterValue = null;
        $ctrl.from = null;
        $ctrl.to = null;
        $ctrl.log = null;
        $ctrl.filters = [];

        function convertDate(date){

        }

        $ctrl.$onInit = function () {
            LogSrv.getAllLogs()
                .then(function (response) {
                    $ctrl.logs = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.search = function () {
            
            if (!$ctrl.log) {
                alert("You need to pick a log type!");
                return;
            }

            if ($ctrl.filters.length == 0) {
                
                if ($ctrl.log === $ctrl.logTypes[0]) {
                    
                    LogSrv.getAllLogs()
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[1]) {
                    
                    LogSrv.getAllDebugLogs()
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[2]) {
                    
                    LogSrv.getAllInfoLogs()
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[3]) {
                    
                    LogSrv.getAllWarnLogs()
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[4]) {
                    
                    LogSrv.getAllErrorLogs()
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
            }
            else if ($ctrl.filters.length == 1) {
                
                if ($ctrl.filters.indexOf($ctrl.filterTypes[0]) > -1) {
                    
                    if (!$ctrl.filterValue) {
                        alert("You need to pick a filter value.");
                        return;
                    }
                    if ($ctrl.log === $ctrl.logTypes[0]) {
                        
                        LogSrv.getAllLogsWithFilter($ctrl.filterValue)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[1]) {
                        
                        LogSrv.getAllDebugLogsWithFilter($ctrl.filterValue)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[2]) {
                        
                        LogSrv.getAllInfoLogsWithFilter($ctrl.filterValue)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[3]) {
                        
                        LogSrv.getAllWarnLogsWithFilter($ctrl.filterValue)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[4]) {
                        
                        LogSrv.getAllErrorLogsWithFilter($ctrl.filterValue)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                }
                else if ($ctrl.filters.indexOf($ctrl.filterTypes[1]) > -1) {
                    
                    if (!$ctrl.to || !$ctrl.from) {
                        alert("You need to properly fill in the dates");
                        return;
                    }
                    console.log(new Date ($ctrl.to));
                    if ($ctrl.log === $ctrl.logTypes[0]) {
                        
                        LogSrv.getAllLogsWithTimestamp($ctrl.from, $ctrl.to)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[1]) {
                        
                        LogSrv.getAllDebugLogsWithTimestamp($ctrl.from, $ctrl.to)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[2]) {
                        
                        LogSrv.getAllInfoLogsWithTimestamp($ctrl.from, $ctrl.to)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[3]) {
                        
                        LogSrv.getAllWarnLogsWithTimestamp($ctrl.from, $ctrl.to)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                    else if ($ctrl.log === $ctrl.logTypes[4]) {
                        
                        LogSrv.getAllErrorLogsWithTimestamp($ctrl.from, $ctrl.to)
                            .then(function (response) {
                                $ctrl.logs = response;
                            })
                            .catch(function (response) {
                                console.log(response);
                            });
                    }
                }
            }
            else if ($ctrl.filters.length == 2) {
                
                if (!$ctrl.filterValue) {
                    alert("You need to pick a filter value.");
                    return;
                }
                if (!$ctrl.to || !$ctrl.from) {
                    alert("You need to properly fill in the dates");
                    return;
                }
                if ($ctrl.log === $ctrl.logTypes[0]) {
                    
                    LogSrv.getAllLogsWithAllFilters($ctrl.filterValue, $ctrl.from, $ctrl.to)
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[1]) {
                    
                    LogSrv.getAllDebugLogsWithAllFilters($ctrl.filterValue, $ctrl.from, $ctrl.to)
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[2]) {
                    
                    LogSrv.getAllInfoLogsWithAllFilters($ctrl.filterValue, $ctrl.from, $ctrl.to)
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[3]) {
                    
                    LogSrv.getAllWarnLogsWithAllFilters($ctrl.filterValue, $ctrl.from, $ctrl.to)
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
                else if ($ctrl.log === $ctrl.logTypes[4]) {
                    
                    LogSrv.getAllErrorLogsWithAllFilters($ctrl.filterValue, $ctrl.from, $ctrl.to)
                        .then(function (response) {
                            $ctrl.logs = response;
                        })
                        .catch(function (response) {
                            console.log(response);
                        });
                }
            }
        };
    }
});