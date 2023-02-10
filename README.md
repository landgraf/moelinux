# moelinux (MangoPi Open Embedded LINUX)
Layer to build Openembedded based distro for MangoPi MQ Pro board


## Setup build environment

Install [repo](https://gerrit.googlesource.com/git-repo) tool (most probably available in your linux distribution)
Install [Yocto Project build requirements](https://docs.yoctoproject.org/ref-manual/system-requirements.html)
```text
mkdir moelinux
cd moelinux
repo init -u https://github.com/landgraf/moelinux.git
repo sync --no-clone-bundle
TEMPLATECONF="../meta-moelinux/conf/templates/moelinux/" . ./core/oe-init-build-env 
bitbake moelinux-image-user
```

The build process takes a while. Resulting image is located under 

```text
tmp-glibc/deploy/images/mangopi-mq-pro/moelinux-image-base-mangopi-mq-pro.wic.gz
```

And be written to MicroSD card with

```
bmaptool copy tmp-glibc/deploy/images/mangopi-mq-pro/moelinux-image-base-mangopi-mq-pro.wic.gz /dev/sdX # Where /dev/sdX is microsd device
```

## Usage
By default image `moelinux-image-user`has user `moeuser` with password `moelinux` created. The password is marked as expired so user will be forced to change one on first login. The user is added to sudoers file and can use sudo to become a root or run applications

## Customization

Image can be customized in the same way as any other OpenEmbedded/YoctoProject/poky image mainly using conf/local.conf file.
For example to add new package(s) to the image simply put 
```
IMAGE_INSTAL:append = " package1 package2"
```
to the end of the file and rebuild the image
```text
bitbake moelinux-image-user
```
Please consult Yocto Project [documentation](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html)


