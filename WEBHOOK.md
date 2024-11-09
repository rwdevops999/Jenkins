# Setting up webhook for push triggering from GitHub

I've a project TestTrigger containing a (Simple) Jenkinsfile

```Jenksfile
pipeline {
    agent {
         label 'macos'
    }

	stages() {
	    stage("stage1") {
	        steps {
	            echo "test 1.18"
	        }
	    }
	}
}
```

## STEP 1: Get Project URL from GitHub

Get Project clone URL from GitHub

```github
>>> Project Repository (TestTrigger) >>> `<> Code` => Copy https URL (https://github.com/rwdevops999/TestTrigger.git)
```

## STEP 2: Jenkins Setup Job (pipeline)

Create Jenkins Pipeline Job.

```Jenkins
New Item >>> (Name)Test + (Type)Pipeline >>> OK
Under General: Build Triggers => CHECK `GitHub hook trigger for GITScm polling`
Under  Pipeline:
>> Under Definition: `Pipeline script from SCM``
>> Under SCM: `Git`
>> Under Repository URL: URL from step 1 (https://github.com/rwdevops999/TestTrigger.git)
>> (Eventually adjust name of Branch Specifier)
>> Script Path: `Jenkinsfile`

=> SAVE
```

## STEP 3: Get Info from Jenkins

Get Webhook Info from Jenkins

```Jenkins
>> Dashboard > Manage Jenkins > System
>> Under GitHub, find GitHub Server and clikc on ? (question mark)
This shows the URL for the webhook (http://localhost:9000/github-webhook/)
```

## STEP 4: Create Jenkins API Key

Create API Secret

```Jenkins
Under Profile (User is top right corner, with the user icon) => Security
>>> Under API Token => Add New Token => Generate (and copy that token: 11910e966d1452c6db07bbeb49a2857ba6) => Save

```

## STEP 5: setup SMEE Webhook

On website `https=//smee.io`

```Smee
=> Start a new Channel
>>> Copy the Webhook Proxy URL (https://smee.io/eDwOxLd2DQ6ct5CR)
>>> install the smee client in a terminal
>>> run the client

smee -t http://localhost:9000/github-webhook/ -u https://smee.io/eDwOxLd2DQ6ct5CR
```

We can see a forwarding message from smee to localhost webhook

## SETP 6: Setup GitHub Webhook

Setting up the GitHub Webhook

```Github
Under the Project folder => Settings
Under Code and Automaton => Webhooks => Add webhook
In Payload URL: enter the smee Webhook Proxy URL (https://smee.io/eDwOxLd2DQ6ct5CR)
content-type = application/json
In Secret, fill in the API key from Jenkins (11910e966d1452c6db07bbeb49a2857ba6)

Leave the rest (Enable SSL Verification, Just the push event, Active) => Add Webhook
```

It should already say : `Last delivery was successful`
And in the terminal: `POST http://localhost:9000/github-webhook/ - 200``

# OK WE'RE SET UP NOW.

Test it with changing some code in the project and push it to GitHub. The pipeline job should be triggered.
The Job should come in the build queue and eventually be executed.
