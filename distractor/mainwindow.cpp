#include "mainwindow.h"

#include <QtGui>
MainWindow::MainWindow (QWidget *parent) : QWidget(parent),ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    //setWindowTitle(tr("Distractor"));
    minTime = 5 * 60000;
    maxTime = 20 * 60000; //time in minutes

    interval = rand() % (maxTime - minTime) + minTime;
    timer.start(interval, this);
}

void MainWindow::timerEvent (QTimerEvent *event) {
    //gets called at a random interval between 5 and 20 mins
    if (event->timerId() == timer.timerId()) {
        //pop the bubble
    } else {
        QWidget::timerEvent(event);
    }
    //reset the timer
    interval = rand() % (maxTime - minTime) + minTime;
    timer.start(interval, this);
}
void MainWindow::on_pushButton_clicked()
{
    //ui.outputWidget->setText(QString::number(value + ui.inputSpinBox2->value()));
    qDebug("Clicked");
}

void MainWindow::on_dial_valueChanged(int value) {

}
