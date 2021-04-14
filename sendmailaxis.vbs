Email_List = "rohan.baraskar@worldline.com;arvind.bangar@worldline.com"

Set App = CreateObject("Outlook.Application")
Set Mail = App.CreateItem(0)

With Mail
    .To = Email_List
    .CC = "abhijeet.zope@worldline.com;pankaj.warghade@worldline.com;gaurav.patil@worldline.com;revathi.nair@worldline.com;dattatraya.agnihotri@worldline.com"
    .BCC = "rohan.baraskar@worldline.com"
    .Subject = "Automation Execution reports"
    .HTMLBody = "Axis Insight Automation Batch Run TestResults"
    '.Body = strbody
    'You can add a file like this
    .Attachments.Add ("C:\demo\E2Eproject\test-output\AxisReport.html")

    'use .Send (to send) or .Display (to display the email and edit before sending)
    .Display
    .send
End With

Set Mail = Nothing
Set App = Nothing