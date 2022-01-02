# This script generates and updates project configuration files.

# We are assuming that project-config is available in sibling directory.
# Checkout from https://github.com/robertvazan/project-config
import sys
sys.path.append('../../project-config/src')

from java import *

repository_name = lambda: 'noexception'
pretty_name = lambda: 'NoException'
pom_version = lambda: '1.7.1'
pom_description = lambda: 'Functional programming for exception handlers.'
inception_year = lambda: 2017
jdk_version = lambda: 11

def dependencies():
    use_slf4j()
    use_junit()
    use_hamcrest()
    use_mockito()
    use('com.github.valfirst:slf4j-test:2.3.0', 'test')

# No link to SLF4J, because automatic modules cannot be linked.
def javadoc_links(): return []

def badges():
    standard_badges()
    print('[![Mentioned in Awesome Java 8](https://awesome.re/mentioned-badge.svg)](https://github.com/tedyoung/awesome-java8)')

generate(globals())
