/*global module:false*/
'use strict';
module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        // Metadata.
        pkg: grunt.file.readJSON('package.json'),
        server: {
            options: {
                port: 3000
            }
        },

        banner: '/*! <%= pkg.title || pkg.name %> - v<%= pkg.version %> - ' +
        '<%= grunt.template.today("yyyy-mm-dd") %>\n' +
        '<%= pkg.homepage ? "* " + pkg.homepage + "\\n" : "" %>' +
        '* Copyright (c) <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>;*/\n',

        // Task configuration.
        open: {
            server: {
                url: 'http://127.0.0.1:<%= server.options.port %>'
            }
        },
        concat: {
            options: {
                banner: '<%= banner %>',
                stripBanners: true
            },
            dist: {
                src: ['app/**/*.js', '.tmp/templates.js'],
                dest: 'dist/<%= pkg.name %>.js'
            }
        },
        uglify: {
            options: {
                banner: '<%= banner %>'
            },
            dist: {
                src: '<%= concat.dist.dest %>',
                dest: 'dist/<%= pkg.name %>.min.js'
            }
        },
        jshint: {
            options: {
                jshintrc: '.jshintrc'
            },
            app: {
                src: 'app/**/*.js'
            }
        },
        watch: {
            injectJS: {
                files: ['app/**/*.js'],
                tasks: ['injector:scripts']
            },
            injectCss: {
                files: ['app/**/*.css'],
                tasks: ['injector:css']
            },
            livereload: {
                files: [
                    'app/**/*.html',
                    'app/**/*.css',
                    'app/**/*.js'
                ],
                options: {
                    livereload: true,
                    interval: 5007
                }
            }
        },
        injector: {
            scripts: {
                files: {
                    'index.html': ['app/js/app.js', 'app/services/*.js', 'app/**/*.js']
                }
            },
            css: {
                files: {
                    'index.html': ['app/**/*.css']
                }
            },
            karma: {
                options: {
                    transform: function (filePath) {
                        return '\'' + filePath.substring(1) + '\',';
                    },
                    starttag: '//-- injector:karma --',
                    endtag: '//-- endinjector:karma --'
                },
                files: {
                    'karma.conf.js': ['app/**/*.js', 'tests/**/*.js']
                }
            }
        },
        wiredep: {
            app: {
                src: 'index.html'
            },
            test: {
                devDependencies: true,
                src: 'karma.conf.js',
                fileTypes: {
                    js: {
                        block: /(([\s\t]*)\/\/\s*bower:*(\S*))(\n|\r|.)*?(\/\/\s*endbower)/gi,
                    }
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-wiredep');
    grunt.loadNpmTasks('grunt-injector');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-open');

    // Default task.
    grunt.registerTask('default', ['concat', 'uglify', 'wiredep', 'injector']);

    grunt.registerTask('checkJsFiles', 'jshint');

    // Used for delaying livereload until after server has restarted
    grunt.registerTask('wait', function () {
        grunt.log.ok('Waiting for server reload...');

        var done = this.async();

        setTimeout(function () {
            grunt.log.writeln('Done waiting!');
            done();
        }, 1500);
    });

    grunt.registerTask('serve', function (target) {
        grunt.log.ok('test' + target);

        grunt.task.run([
            'injector',
            'wiredep',
            'wait',
            'open',
            'watch'
        ]);
    });
};