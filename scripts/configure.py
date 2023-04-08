# This script generates and updates project configuration files.

# Run this script with rvscaffold in PYTHONPATH
import rvscaffold as scaffold

class Project(scaffold.Java):
    def script_path_text(self): return __file__
    def repository_name(self): return 'noexception'
    def is_member_project(self): return True
    def pretty_name(self): return 'NoException'
    def pom_description(self): return 'Functional programming for exception handlers.'
    def inception_year(self): return 2017
    def stagean_annotations(self): return True
    def project_status(self): return self.stable_status()
    
    def dependencies(self):
        yield from super().dependencies()
        yield self.use_closeablescope()
        # Used only in deprecated APIs.
        yield self.use_slf4j()
        yield self.use_junit()
        yield self.use_hamcrest()
        yield self.use_mockito()
        # Used only in deprecated APIs.
        yield self.use_slf4j_test()

    # No link to SLF4J, because automatic modules cannot be linked.
    def javadoc_links(self):
        yield from super().javadoc_links()
        yield 'https://closeablescope.machinezoo.com/javadoc/'

    def print_badges(self):
        super().print_badges()
        print('[![Mentioned in Awesome Java 8](https://awesome.re/mentioned-badge.svg)](https://github.com/tedyoung/awesome-java8)')

Project().generate()
