import os

# Python script to compare the contents of the lib_sdk/ vs lib/ directories.
# Chris Joakim, 2016/04/20
#
# Usage:
# $ python libs.py


def list_files(dir):
    d = dict()
    for dir_name, subdirs, base_names in os.walk(dir):
        for base_name in base_names:
            fullname = "{}/{}".format(dir_name, base_name)
            if base_name.endswith('.jar'):
                d[base_name] = fullname
    return d

if __name__ == "__main__":
    d1 = list_files('lib/')
    d2 = list_files('lib_sdk/')

    for key in sorted(d2.keys()):
        if 'sources.jar' in key:
            pass
        else:
            if key in d1:
                print('{0}  {1}'.format('present', key))
            else:
                print('{0}  {1}'.format('absent ', key))
