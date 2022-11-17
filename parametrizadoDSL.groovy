job('ejemplo2-job-DSL') {
  description('Job DSL de ejemplio para el curso de jenkins') 
  	scm {
    	git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
      	node / gitConfigName ('macloujulian')
      	node / gitConfigEmail('macloujulian@gmail.com')
    	}
  	}  
  parameters {
    	stringParam('nombre', defaultValue = 'christian', description = 'parametro de cadena para el job bolean')
    	booleanParam('agente', false)
        choiceParam('planeta', ['Mercurio (default)', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Saturno', 'Urano'])
  }
  triggers {
   	  cron('H/7 * * * *')
  }
  steps {
      shell('Bash jobscript.sh')
  }
  publishers {
    mailer('ccueva235@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }  
}
