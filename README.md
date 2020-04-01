## Ocado Queue Poller

A simple web service to poll the Ocado Supermarket's queue front page and notify users via
email when delivery slots become available. 

**NB** This poller doesn't do anything nefarious like jump the position in the queue, it simply
checks periodically to see if slots are available as if a normal user would. The purpose is to
help people who aren't able to always be infront of a device to check this.

### Prerequisites

- Google cloud SDK installed ([here](https://cloud.google.com/sdk/install))

### Usage

- Create a new project called ```ocado-poller``` on GCP to host the Google App Engine service (this 
might have a number appended to it by GCP to make it unique, if so you will need to update your 
```build.gradle``` ```appEngineProjectId``` field to reflect this change)
- In the root of the project directory, run ```gcloud init```
- In the root of the project directory, run ```gcloud app create```. You will be asked to set your
region - it makes sense to choose somewhere in the UK or Europe as this is closest to Ocado's data centres 
(presumably).
- Run ```./gradlew appenginedeploy``` to deploy the application to GCP.

- If you make a change to the service and want to re-deploy it, don't forget to update the ```version```
in the ```build.gradle``` or you won't be able to deploy the new version of the service because GCP
won't be able to split the traffic between the two services.

### Hints

To avoid charges from running this service make sure that the project is disabled in GCP by going to
```App Engine --> Settings``` and clicking the ```Disable``` button.
