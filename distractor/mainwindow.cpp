#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QtWidgets>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    setWindowTitle(tr("Distractor"));
    minTime = 5 * 60000;
    maxTime = 20 * 60000; //time in minutes

    interval = rand() % (maxTime - minTime) + minTime;
    timer.start(interval, this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::timerEvent (QTimerEvent *event) {
    //gets called at a random interval between 5 and 20 mins

    //reset the timer
    interval = rand() % (maxTime - minTime) + minTime;
    timer.start(interval, this);
}
