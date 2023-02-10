SUMMARY = "WIFI/BT Binary Blobs for rtl8723ds-bt (btrtl module)"
HOMEPAGE = "https://github.com/Realtek-OpenSource/android_hardware_realtek/tree/rtk1395/bt/rtkbt/Firmware/BT"
LICENSE = "CLOSED"

SRC_URI = "https://github.com/Realtek-OpenSource/android_hardware_realtek/raw/rtk1395/bt/rtkbt/Firmware/BT/rtl8723ds_fw;name=rtl8723ds_fw;downloadfilename=rtl8723ds_fw.bin \
           https://github.com/Realtek-OpenSource/android_hardware_realtek/raw/rtk1395/bt/rtkbt/Firmware/BT/rtl8723ds_config;name=rtl8723ds_config;downloadfilename=rtl8723ds_config.bin \
          "
SRC_URI[rtl8723ds_fw.sha256sum] = "e4d891489337086e61a6be5d4544b918fabf199e2261550c208d5a2a2d2cdcd0"
SRC_URI[rtl8723ds_config.sha256sum] = "250b4283720229c5f2aac31f57f12dabbd54ad512a22e7eb15c13266aa11d30c"


S = "${WORKDIR}"

inherit allarch

CLEANBROKEN = "1"

do_configure[noexec] ="1"

do_install () {
  install -d -m 0755  ${D}${nonarch_base_libdir}/firmware/rtl_bt/
  install -Dm 0644 ${S}/rtl8723ds_fw.bin  ${D}${nonarch_base_libdir}/firmware/rtl_bt/
  install -Dm 0644 ${S}/rtl8723ds_config.bin  ${D}${nonarch_base_libdir}/firmware/rtl_bt/
}


FILES:${PN} =  "${nonarch_base_libdir}/firmware/rtl_bt/"
INSANE_SKIP =  "arch"
## Pin to mangopi-mq-pro for now, Other machines must be verified before using
COMPATIBLE_MACHINE = "mangopi-mq-pro"
