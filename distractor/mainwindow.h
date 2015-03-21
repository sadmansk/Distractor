#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QBasicTimer>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

protected:
    void timerEvent (QTimerEvent *event) Q_DECL_OVERRIDE;

private:
    Ui::MainWindow *ui;
    QBasicTimer timer;
    //set up timer parameters
    int minTime, maxTime;
    int interval;
};

#endif // MAINWINDOW_H
