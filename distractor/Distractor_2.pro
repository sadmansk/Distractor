#-------------------------------------------------
#
# Project created by QtCreator 2015-03-21T21:20:29
#
#-------------------------------------------------

QT += core gui
QT += core gui network webkit webkitwidgets

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Distractor_2
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    bubble.cpp

HEADERS  += mainwindow.h \
    bubble.h

FORMS    += mainwindow.ui \
    bubble.ui
