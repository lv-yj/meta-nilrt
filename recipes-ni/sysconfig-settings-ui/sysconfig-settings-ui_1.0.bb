SUMMARY = "System configuration files to enable UI"
DESCRIPTION = "Configuration files to enable UI for the National Instruments System Configuration subsystem."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "base"

SRC_URI = "file://uixml/* \
	   file://systemsettings/ui_enable.ini \
"

FILES_${PN} += "/usr/local/natinst/share/uixml/sysconfig/* \
"

DEPENDS = "niacctbase"
RDEPENDS_${PN} = "sysconfig-settings niacctbase"

S = "${WORKDIR}"

user = "${LVRT_USER}"
group = "${LVRT_GROUP}"

do_install () {
	# UIXML config (soft dip switches, etc.)
	install -d ${D}/usr/local/natinst/share/uixml/sysconfig/
	install -m 0644 ${WORKDIR}/uixml/* ${D}/usr/local/natinst/share/uixml/sysconfig/

	# Interface for enabling UI for System Configuration
	install -d -m 0775 ${D}${localstatedir}/local/natinst/systemsettings/
	chown ${user}:${group} ${D}${localstatedir}/local/natinst/systemsettings/
	install -m 0644 ${WORKDIR}/systemsettings/ui_enable.ini ${D}${localstatedir}/local/natinst/systemsettings/
}
