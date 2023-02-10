SUMMARY = "Mount persistent filesystem"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://input.mount"

REQUIRED_DISTRO_FEATURES ?= "systemd"
inherit persistent features_check

DEPENDS:append = "systemd"
do_compile[noexec]="1"

SERVICE_NAME .= "${@ d.getVar('PERSIST_FS_LABEL')[7:] }"

do_configure(){
	sed -i 's~@LABEL@~${PERSIST_FS_LABEL}~g' "${WORKDIR}/input.mount"
	sed -i 's~@MOUNTPOINT@~${PERSIST_MOUNT_POINT}~g' "${WORKDIR}/input.mount"
}

do_install() {
    install -d "${D}${systemd_unitdir}/system"
    install -d "${D}/${PERSIST_MOUNT_POINT}"
    install -m0755 "${WORKDIR}/input.mount" "${D}${systemd_unitdir}/system/${SERVICE_NAME}.mount"
}

SYSTEMD_SERVICE:${PN} = "${SERVICE_NAME}.mount"
SYSTEMD_AUTO_ENABLE = "enable"


FILES:${PN}:append = " ${PERSIST_MOUNT_POINT}"
FILES:${PN}:append = " ${systemd_unitdir}/system/*.mount"