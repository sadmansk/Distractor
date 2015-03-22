#include "mainwindow.h"
#include <iostream>
#include <iomanip>
#include <locale>
#include <sstream>
#include <QtGui>
MainWindow::MainWindow (QWidget *parent) : QWidget(parent),ui(new Ui::MainWindow), bubbles()
{
    ui->setupUi(this);
    //setWindowTitle(tr("Distractor"));
    minTime = 1000;
    maxTime = 10 * 1000; //time in minutes
    interval = rand() % (maxTime - minTime) + minTime;
    timer.start(interval, this);
}

MainWindow::~MainWindow(){
    while(bubbles.size() > 0){
        delete bubbles.back();
        bubbles.pop_back();
    }
}

void MainWindow::timerEvent (QTimerEvent *event) {
    //gets called at a random interval between 5 and 20 mins
    if (event->timerId() == timer.timerId()) {
        Bubble *newBubble = new Bubble;
        newBubble->show();
        bubbles.push_back(newBubble);
        if (maxTime > 250){
            maxTime /= 2;
        }
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
    qDebug(ui->textEdit->toPlainText().toLocal8Bit().data());
    //ui->textEdit->addItem(ui->textEdit->toPlainText());
    qDebug("Clicked");
}

void MainWindow::on_dial_valueChanged(int value) {
    std::ostringstream convert;
    convert<<value;
    qDebug(convert.str().c_str());
    minTime = (int)((abs (value - 50) + 100) * 50);
    maxTime = minTime * 2;
    minTime = 0;
    interval = rand() % (maxTime - minTime) + minTime;
    timer.start(interval, this);
    std::cout << maxTime << std::endl;
}
int MainWindow::getDial()
{
    return dialSetting;
}
