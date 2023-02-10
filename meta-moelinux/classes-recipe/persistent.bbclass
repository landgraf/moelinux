# SPDX-FileCopyrightText: Pavel Zhukov <pavel@zhukoff.net>
#
# SPDX-License-Identifier: MIT

# This class provides support of symlinking of files/directories which should be kept between
# reflash (of the microSD card).
# Good examples for this are:
#      /etc/NetworkManager/system-connections
#      /etc/ssh/ <keys>
#      /etc/bluetooth

# Files specified in PERSIST should have corresponding path under PERSIST_DIR
# (/data). Example:

# /etc/NetworkManager/system-connections -> /data/etc/NetworkManager/system-connections

inherit systemd

PERSIST_FS_LABEL = "moeper-mnt-data"
PERSIST_MOUNT_POINT = "${@ d.getVar('PERSIST_FS_LABEL')[7:].replace('-','/') }"

RDEPENDS:${PN} += "moelinux-persist"

def create_link(filename, install_dir, link_prefix):
    dst = os.path.join(install_dir, filename.strip(os.path.sep))
    src = os.path.join(link_prefix, filename.lstrip(os.path.sep))
    if os.path.exists(dst):
        os.rename(dst, "{}.vendor".format(dst))
    os.symlink(src, dst)

python create_links() {
    import os
    install_dir = d.getVar("D")
    prefix = d.getVar("PERSIST_MOUNT_POINT")
    to_persist = d.getVar("PERSIST_FILE")
    if to_persist is None:
      return
    for f in to_persist.split():
        create_link(f, install_dir, prefix)
}

do_install[postfuncs] += "create_links"
