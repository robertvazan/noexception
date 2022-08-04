# This script generates and updates project configuration files.

# We are assuming that project-config is available in sibling directory.
# Checkout from https://github.com/robertvazan/project-config
import pathlib
project_directory = lambda: pathlib.Path(__file__).parent.parent
config_directory = lambda: project_directory().parent/'project-config'
exec((config_directory()/'src'/'java.py').read_text())

project_script_path = __file__
repository_name = lambda: 'noexception'
pretty_name = lambda: 'NoException'
pom_description = lambda: 'Functional programming for exception handlers.'
inception_year = lambda: 2017
jdk_version = lambda: 11
stagean_annotations = lambda: True

def dependencies():
    use_slf4j()
    use_junit()
    use_hamcrest()
    use_mockito()
    use_slf4j_test()

# No link to SLF4J, because automatic modules cannot be linked.
javadoc_links = lambda: standard_javadoc_links()

def badges():
    standard_badges()
    print('[![Mentioned in Awesome Java 8](https://awesome.re/mentioned-badge.svg)](https://github.com/tedyoung/awesome-java8)')

generate()
