![Promo logo](docs/img/promo-logo-png.png)

Last `master` branch's (version 1.0.4) statistics :

[![Build Status](https://jenkins.tristan.moe/job/Lyrebird/job/master/badge/icon)](https://jenkins.tristan.moe/job/Lyrebird/job/master)
[![Sonar bugs](https://sonar.tristan.moe/api/project_badges/measure?project=moe.lyrebird%3Alyrebird-parent&metric=bugs)](https://sonar.tristan.moe/project/issues?id=moe.lyrebird%3Alyrebird-parent&resolved=false&types=BUG)
[![Lines of code](https://sonar.tristan.moe/api/project_badges/measure?project=moe.lyrebird%3Alyrebird-parent&metric=ncloc)](https://sonar.tristan.moe/component_measures?id=moe.lyrebird%3Alyrebird-parent&metric=ncloc)
[![Known Vulnerabilities](https://snyk.io/test/github/tristan971/lyrebird/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/tristan971/lyrebird?targetFile=pom.xml)

[![Quality gate status](https://sonar.tristan.moe/api/project_badges/quality_gate?project=moe.lyrebird%3Alyrebird-parent)](https://sonar.tristan.moe/dashboard?id=moe.lyrebird%3Alyrebird-parent)

## Reasoning :
Currently there are no good Twitter clients on Windows and Linux.
The aim is to fix this, but also support macOS, and do all of that while being fully open-source.

Hopefully this works out.


## How to get it

#### [Precompiled releases](https://github.com/Tristan971/Lyrebird/releases)

#### Build it yourself

Requirements:
- A Java 10+ development kit with JavaFX components included (i.e. an Oracle JDK or an OpenJDK+OpenJFX combo)
- Apache Maven (works at least with version 3.5.2)
- For native images you need either InnoSetup tools on Windows or ``dpkg-deb``/``rpmutils`` on Linux depending on
what system you want to target. You can only build native images for the current system type.

Any of the following (execute command in this README's directory):
- Execute only (no packaging): ``cd lyrebird && mvn clean spring-boot:run``
- For a native image: ``cd lyrebird && mvn clean jfx:native -DskipTests``
- For a portable JAR file ``mvn clean package -DskipTests``

## What does it look like?
[![Screenshot of current version](docs/img/screenshot.png)](docs/img/screenshot.png)

## Current state of development:

- [x] Log in 
- [ ] Tweets-based systems
    - [x] Base systems :
        - [x] Timeline
        - [x] Mentions
        - [x] Direct messages
    - [ ] Display features :
        - [x] Basic textual display
        - [x] Display username, @screenname and user profile picture
        - [ ] Display for chosen list
        - [ ] Text highlighting
            - [ ] Mentions
                - [ ] Color
                - [ ] Clickable
            - [ ] Hashtags
                - [ ] Color
                - [ ] Clickable
            - [x] URLs
                - [x] Color
                - [x] Clickable
        - [ ] Media embedding
            - [ ] Officially supported
                - [x] Twitter image embedding
                - [x] Twitter video embedding
                - [ ] Twitter gif images embedding
            - [ ] Easy embedding
                - [ ] Direct image embedding
                - [ ] Direct video embedding
            - [ ] Why is it not easy to do ?
                - [ ] Youtube video embedding
    - [x] Data loading features :
        - [x] Seeking older tweets by-demand (scroll or button)
        - [x] Seek newer tweets all the time using streaming API

- [ ] Tweeting
    - [x] Support text
    - [x] Support attachments
    - [ ] Support geolocation
    
- [ ] Interraction with tweet
    - [ ] Reply
    - [ ] Quote
    - [x] Like (formerly _favourite_)
    - [x] Retweet

- [ ] Searches

## Can I contribute?
Please do!

It should be fairly easy to understand the general idea of the back-end model for any
seasoned Java developer with some Spring Framework experience.

For the UI/JavaFX model a lot of the work is delegated to a JavaFX framework called
EasyFXML which you can read about [here](https://github.com/Tristan971/EasyFXML).
