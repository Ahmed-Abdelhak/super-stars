package com.redcare.pharmacy.main

import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class ApplicationStartupListener(private val githubService: GithubService) :
    ApplicationListener<ApplicationReadyEvent> {

    private val logger = KotlinLogging.logger {}
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        logger.info {
            "Count of Repositories: " +
                    githubService.getPopularRepositories().size +
            " , RepositoriesList: " + githubService.getPopularRepositories()
        }
    }

}