# INFO5100-Jan2020-Project
This repository is for all members of INFO 5100 - Jan 2020 class collaborative project work

## Setup (only need to do once per laptop)
#### SSH key
- Generate an SSH key following this guide: https://help.github.com/en/github/authenticating-to-github/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent
- Then add the SSH key to your Github account following this guide: https://help.github.com/en/github/authenticating-to-github/adding-a-new-ssh-key-to-your-github-account

#### Clone your team's repo
```
git clone <your team's repo address>
```
The repo address can be found if you go to the repo's webpage, and click the green `Clone or download` button.
Then simply copy and paste it.

## Routine commands every time you modify the code
Every time before you start making changes, make sure to run
```
git pull
```
This will pull any and all update in the repo to your local workspace<br/>
<br/>
After you finish coding&testing and ready to submit
```
git add <name of file you want to submit>
```
or simply
```
git add --all
```
if you want to submit all of your changes. Then run
```
git commit -m "<your commit message>" (Make sure to write descriptive commit messages)
```
And finally
```
git push
```

## Further learning
Since we are using a forked repo from the class's main repo, if you are interested in learning how to sync
changes in main repo to the forked repo: https://gist.github.com/CristinaSolana/1885435

## Notes
- You are always welcome to reach out to Arch for help with Git
- Google.com is also a good place to look for help
