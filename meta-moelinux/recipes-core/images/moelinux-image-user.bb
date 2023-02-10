require moelinux-image-common.inc

inherit extrausers

IMAGE_INSTALL:append = " moelinux-sudo" 
EXTRA_IMAGE_FEATURES:append = " package-management"
MOEUSERNAME = "moeuser"

## Username: moeuser
## Password: moelinux
EXTRA_USERS_PARAMS = "\
    useradd -p '\$5\$dgBZkl6Zn1O8vyJP\$H5d6xmRhoHERIwWPaz.g9bNwm2HulBpGfVnf8/xYbxD' ${MOEUSERNAME}; \
    passwd-expire ${MOEUSERNAME}; \
    "