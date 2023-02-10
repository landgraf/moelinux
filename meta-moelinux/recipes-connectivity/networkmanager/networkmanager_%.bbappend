inherit persistent

RDEPENDS:${PN}-daemon:append = "moelinux-persist"
PERSIST_FILE:append="/etc/NetworkManager/system-connections/"
