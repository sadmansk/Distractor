#include "settingswindow.h"
#include <QtWidgets>

settingsWindow::settingsWindow()
{
    settingswindowLabel=new QLabel;
    settingswindowLabel->setSizePolicy(QSizePolicy::Expanding, QSizePolicy::Expanding);
    settingswindowLabel->setAlignment(Qt::AlignCenter);

    mainLayout = new QVBoxLayout;
    mainLayout->addWidget(settingsWindowLabel);


    setWindowTitle(tr("Screenshot"));
}

settingsWindow::~settingsWindow()
{

}

