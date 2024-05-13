package com.ritense.valtimoprocesstestrunner.interaction.instance

import com.ritense.valtimoprocesstestrunner.command.TestrunCommand
import com.ritense.valtimoprocesstestrunner.command.TestrunCommandService
import com.ritense.valtimoprocesstestrunner.event.TestrunEvent
import com.ritense.valtimoprocesstestrunner.mapper.TestrunMapperService
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceRepository
import com.ritense.valtimoprocesstestrunner.testrun.instance.TestrunInstanceService
import mu.KotlinLogging

class TestrunInteractionInstanceServiceImpl(
    private val commandService: TestrunCommandService,
    private val testrunInteractionInstanceRepository: TestrunInteractionInstanceRepository,
    private val testrunInstanceRepository: TestrunInstanceRepository,
    private val mapperService: TestrunMapperService
): TestrunInteractionInstanceService {
    override fun handleEvent(event: TestrunEvent) {
        testrunInstanceRepository.findByDocumentId(event.documentId).ifPresent {
            val testrunInteractionInstance = testrunInteractionInstanceRepository
                .findAllByTestrunInstance(it)
                .firstOrNull {it.eventDefinition.equals(event.eventDefinition())}

            if(testrunInteractionInstance != null) {
                val command: TestrunCommand = mapperService.eventToCommand(
                    event,
                    testrunInteractionInstance.commandDefinition.getCommandType(),
                    testrunInteractionInstance
                )
                dispatchCommand(command)
            } else {
                logger.info("No interaction instance found for event definition")
            }
        }
    }

    override fun createInteractionInstance(interactionInstance: TestrunInteractionInstance) {
        testrunInteractionInstanceRepository.save(interactionInstance)
    }

    private fun dispatchCommand(command: TestrunCommand): Any {
        return commandService.handleCommand(command)
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}