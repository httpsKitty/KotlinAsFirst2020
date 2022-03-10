Создал fork
Добавил upstream с помощью команды git remote add upstream https://github.com/httpsKitty/KotlinAsFirst2021
Выполнил fetch git fetch upstream
Перешел на upstream/master git chackout upstream/master
Создал ветку backport из upstream от ветки master от коммита d535f3592006b8f2593c9f881d72c05164aadc13 git checkout -b backport d535f3
Запушил ветку git push --set-upstream origin backport
Добавил upstream-other с помощью команды git remote add upstream-other https://github.com/femboy-dev/KotlinAsFirst2020
Перешел на master git chackout master
Сделал мердж ветки master из remote upstream-other git merge upstream-other/master
Создал файл и назвал его remotes
Зафиксировал все изменения git add .
Закомитил файл remotes с помощью git commit -m "Add remotes file"
Загрузил изменения на GitHub через git push --set-upstream origin master
Создал файл howto.md.
Зафиксировал все изменения, используя git add .
Закомитил файл howto.md git commit -m "Add howto.md file"
Загрузил изменения на GitHub git push