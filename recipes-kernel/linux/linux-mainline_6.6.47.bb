DESCRIPTION = "Linux Kernel from Tarball"
SECTION = "kernel"
LICENSE = "GPLv2"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = "\
    https://cdn.kernel.org/pub/linux/kernel/v6.x/linux-${PV}.tar.xz \
    file://001-second_core_support_in_platsmp.patch \
    file://002-enable-serial5-for-linux-console.patch \
    file://defconfig \
    "
SRC_URI[sha256sum] = "d43376c9e9eaa92bb1b926054bd160d329c58a62d64bd65fe1222c11c6564f50"

LINUX_VERSION ?= "${PV}"
LINUX_VERSION_EXTENSION_append = "-custom"

S = "${WORKDIR}/linux-${PV}"
COMPATIBLE_MACHINE = "sun8i"

do_install_prepend() {
    mkdir -p "${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}"
    touch "${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/source"
}
