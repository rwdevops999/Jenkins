/**
* mail html body (red for failure, green for success) to recipient (config:to) and attach eventually the log
*/
def call(Map config = [:]) {
    def status = currentBuild.result ? (currentBuild.result == 'SUCCESS' ? 'SUCCESS' : 'FAILURE') : 'NOTIFICATION'
    def htmlBody = libraryResource 'mail-template.html'
    def htmlBindings = [
            jobName        : env.JOB_NAME,
            buildNumber    : env.BUILD_NUMBER,
            url            : env.BUILD_URL,
            pipelineStatus : status,
            bannerColor    : currentBuild.result ? (currentBuild.result == 'SUCCESS' ? 'green' : 'red') : 'blue'
    ]
    def body = renderTemplate(htmlBody,htmlBindings)
    emailext(
         subject: "${env.JOB_NAME} - Build: ${env.BUILD_NUMBER} - Pipeline Status: ${status}",
         body: body,
         to: config.to,
         from: 'admin@jenkins.com',
         replyTo: 'no-reply@jenkins.com',
         mimeType: 'text/html',
         attachLog: config.attachLog
    )
}