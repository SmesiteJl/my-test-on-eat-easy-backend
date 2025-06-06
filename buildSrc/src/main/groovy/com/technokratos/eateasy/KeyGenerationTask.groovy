package com.technokratos.eateasy

import org.gradle.api.Plugin
import org.gradle.api.Project

class KeyGenerationTask implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.register('generateRsaKeys') {
            description = 'Generates RSA key pairs for access and refresh tokens'

            def keyDir = new File("${project.projectDir}/src/test/resources/security")
            def keySize = 2048

            doLast {
                keyDir.mkdirs()

                logger.lifecycle("Generating ACCESS token keys...")
                generateKeyPair('access', project, keyDir, keySize)
                logger.lifecycle("Generating REFRESH token keys...")
                generateKeyPair('refresh', project, keyDir, keySize)

                logger.lifecycle("All keys have been successfully created in ${keyDir}")
            }
        }

        // Генерим перед тестами
        project.tasks.named('processTestResources') {
            dependsOn project.tasks.named('generateRsaKeys')
        }
    }

    private void generateKeyPair(String name, Project project, File keyDir, int keySize) {
        generatePublicKey(name, project, keyDir, keySize)
        generatePrivateKey(name, project, keyDir)
    }


    private void generatePublicKey(String name, Project project, File keyDir, int keySize) {
        project.exec {
            commandLine 'openssl', 'genpkey',
                    '-algorithm', 'RSA',
                    '-out', "${keyDir}/${name}_private.pem",
                    '-pkeyopt', "rsa_keygen_bits:${keySize}"
        }
    }

    private void generatePrivateKey(String name, Project project, File keyDir) {
        project.exec {
            commandLine 'openssl', 'rsa',
                    '-pubout',
                    '-in', "${keyDir}/${name}_private.pem",
                    '-out', "${keyDir}/${name}_public.pem"
        }
    }
}
