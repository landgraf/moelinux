DESCRIPTION = "Enable sudo for moelinux users"

do_unpack[noexec] = "1"
do_compile[noexec] = "1"

MOEUSERNAME ??= "username_must_be_changed"
LICENSE = "MIT"
RDEPENDS:${PN} = "sudo"

do_configure(){
	mkdir -p ${WORKDIR}/etc/sudoers.d/
	echo "${MOEUSERNAME} ALL=(ALL:ALL) ALL" > ${WORKDIR}/${MOEUSERNAME}.tmp
	echo "Defaults secure_path=/bin:/usr/bin:/usr/local/bin:/sbin:/usr/sbin:/usr/local/sbin" >>  ${WORKDIR}/${MOEUSERNAME}.tmp
}

do_install(){
	install -d -m750 ${D}/etc/sudoers.d/
	install -m640 ${WORKDIR}/${MOEUSERNAME}.tmp  ${D}/etc/sudoers.d/${MOEUSERNAME}
}

FILES:${PN}:append = " /etc/sudoers.d/${MOEUSERNAME}.conf"