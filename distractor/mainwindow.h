#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "ui_mainwindow.h"
#include <QWidget>
#include <QBasicTimer>

namespace Ui {
class MainWindow;
}

class MainWindow : public QWidget
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    //~MainWindow();
    Ui::MainWindow *ui;

protected:
    void timerEvent (QTimerEvent *event) Q_DECL_OVERRIDE;

private:
    QBasicTimer timer;
    //set up timer parameters
    int minTime, maxTime;
    int interval;

private slots:
    void on_pushButton_clicked();
    void on_dial_valueChanged(int value);
};
#endif
