Email_List = "rohan.baraskar@worldline.com;arvind.bangar@worldline.com"

Set App = CreateObject("Outlook.Application")
Set Mail = App.CreateItem(0)

With Mail
    .To = Email_List
    .CC = "rohan.baraskar@worldline.com"
    .BCC = "rohan.baraskar@worldline.com"
    .Subject = "Automation Execution reports"
    .HTMLBody = "Axis Insight Automation Batch Run TestResults"
    '.Body = strbody
    'You can add a file like this
    .Attachments.Add ("C:\demo\E2Eproject\test-output\AxisReport.html")
                .Attachments.Add ("C:\demo\E2Eproject\Screenshot\final.png")

    'use .Send (to send) or .Display (to display the email and edit before sending)
    .Display
    .send
End With

Set Mail = Nothing
Set App = Nothing
