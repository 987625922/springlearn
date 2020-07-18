## git
1. **图解**
2. **[创建仓库](#创建仓库)**
3. **[添加文件到缓存区](#添加文件到缓存区)**
4. **[查看当前状态(文件级别的改动  git status)](#查看当前状态)**
5. **[显示已写入缓存的改动（代码级别的，改动的代码一行一行显示）](#显示已写入缓存的改动)**
6. **[缓存区内容添加到仓库目录中](#缓存区内容添加到仓库目录中)**
7. **[取消已缓存的内容（多个文件加入缓存区之后，可以通过命令取消其中一个文件）](#取消已缓存的内容)**
8. **[检出](#检出)**
9. **[忽略文件](#忽略文件)**
10. **[撤销提交](#撤销提交)**
11. **[日志与历史](#日志与历史)**
12. **[分支](#分支)**
13. **[解决冲突](#解决冲突)**
14. **[远程仓库操作](#远程仓库操作)**

- **图解**

  工作目录 ---git add---> 缓存目录 ---git commit---> 仓库目录

- **<a id="创建仓库">创建项目仓库</a>**
```
git init/clone
```
- **<a id="添加文件到缓存区">添加文件到缓存区</a>**
```
#把文件加入缓存区
git add 文件名加后缀/.

#可以撤销add的提交
git reset HEAD 文件名/. 
```
- **<a id="查看当前状态">查看当前状态(文件级别的改动  git status)</a>**
    ```
    #（加 -s 参数，以获得简短的结果输出）
    git status 
    ```
    - A: 你本地新增的文件（服务器上没有）.
    - C: 文件的一个新拷贝.
    - D: 你本地删除的文件（服务器上还在）.
    - M: 文件的内容或者mode被修改了.
    - R: 文件名被修改了。
    - T: 文件的类型被修改了。
    - U: 文件没有被合并(你需要完成合并才能进行提交)。
    - X: 未知状态(很可能是遇到git的bug了，你可以向git提交bug report)
    - ？：未被git进行管理，可以使用git add file1把file1添加进git能被git所进行管理
- **<a id="显示已写入缓存的改动">显示已写入缓存的改动（代码级别的，改动的代码一行一行显示 git diff）</a>**
  - 尚未缓存的改动：**git diff**

  - 比较暂存区的文件与之前已经提交过的文件： **git diff --cached**

  - 查看仓库目录的与工作目录文件的差异：**git diff HEAD**

- **<a id="缓存区内容添加到仓库目录中">缓存区内容添加到仓库目录中</a>**

  ```
  #（使用 -m 选项以在命令行中提供提交注释，-am 跳过git add步骤） 
  git commit 
  
  #此命令可以用来回退到任意版本
  git reset --hard commitid ** 
  ```
- **<a id="取消已缓存的内容">取消已缓存的内容（多个文件加入缓存区之后，可以通过命令取消其中一个文件）</a>**

  ```
  #文件名加后缀   （从缓存区中取消一个文件）
  git reset HEAD
  
  #只从缓存区中删除，保留物理文件
  git rm --cached readme.txt 
  
  #不但从stage中删除，同时删除物理文件
  git rm readme.txt 
  ```

- **<a id="检出">检出</a>**

  ```
  #git checkout命令会用最近提交的版本覆盖掉它
  git checkout (文件名加后缀/.)
  
  #此命令可以用来回退到任意版本
   reset --hard commitid **
   
  #可以撤销add的提交
  git reset HEAD 文件名/. 
  ```

- **<a id="忽略文件">忽略文件</a>**

  在主目录创建“.gitignore”文件,此文件有如下规则：

  1. 忽略文件中的空行或以井号（#）开始的行将会被忽略。
  2. 可以使用Linux通配符。例如：星号（*）代表任意多个字符，问号（？）代表一个字符，方括号（[abc]）代表可选字符范围，大括号（{string1,string2,...}）代表可选的字符串等。
  3. 如果名称的最前面有一个感叹号（!），表示例外规则，将不被忽略。
  4. 如果名称的最前面是一个路径分隔符（/），表示要忽略的文件在此目录下，而子目录中的文件不忽略。
  5. 如果名称的最后面是一个路径分隔符（/），表示要忽略的是此目录下该名称的子目录，而非文件（默认文件或目录都忽略）。

- **<a id="撤销提交">撤销提交</a>**

  ```
  #撤销上一次的提交（ 
  # --soft 仅仅移动当前Head指针，不会改变工作区和暂存区的内容
  #--mixed 是reset的默认参数,移动head指针，改变暂存区内容，但不会改变工作区
  #--hard 当前head指针、工作区和暂存区内容全部改变
  ）
  git reset --hard commitId
  
  #撤销某个commit的提交，这个commit可以是第一，二，三个
  git revert <commit-id>
  ```

- **<a id="日志与历史">日志与历史</a>**

  ```
  #查看提交日志
  git log （--graph 查看分支合并图）
  ```

- **<a id="分支">分支</a>**

  ```
  # 列出所有本地分支
  $ git branch
  
  # 列出所有远程分支
  $ git branch -r
  
  # 列出所有本地分支和远程分支
  $ git branch -a
  
  # 新建一个分支，但依然停留在当前分支
  $ git branch [branch-name]
  
  # 新建一个分支，并切换到该分支
  $ git checkout -b [branch]
  
  # 新建一个分支，指向指定commit
  $ git branch [branch] [commit]
  
  # 新建一个分支，与指定的远程分支建立追踪关系
  $ git branch --track [branch] [remote-branch]
  
  # 切换到指定分支，并更新工作区
  $ git checkout [branch-name]
  
  # 切换到上一个分支
  $ git checkout -
  
  # 建立追踪关系，在现有分支与指定的远程分支之间
  $ git branch --set-upstream [branch] [remote-branch]
  
  # 合并指定分支到当前分支
  $ git merge [branch]
  
  # 选择一个commit，合并进当前分支
  $ git cherry-pick [commit]
  
  # 删除分支
  $ git branch -d [branch-name]
  
  # 删除远程分支
  $ git push origin --delete [branch-name]
  $ git branch -dr [remote/branch]
  ```

- **<a id="解决冲突">解决冲突</a>**

  在更新或者合并的时候会出现冲突，这时候需要把冲突文件修改之后再add . commit -m "修改了冲突文件" push 重新提交

- **<a id="远程仓库操作">远程仓库操作</a>**

  ```
  # 下载远程仓库的所有变动
  $ git fetch [remote]
  
  # 显示所有远程仓库
  $ git remote -v
  
  # 显示某个远程仓库的信息
  $ git remote show [remote]
  
  # 增加一个新的远程仓库，并命名
  $ git remote add [shortname] [url]
  
  # 取回远程仓库的变化，并与本地分支合并
  $ git pull [remote] [branch]
  
  # 上传本地指定分支到远程仓库
  $ git push [remote] [branch]
  
  # 强行推送当前分支到远程仓库，即使有冲突
  $ git push [remote] --force
  
  # 推送所有分支到远程仓库
  $ git push [remote] --all
  
  #简单查看远程---所有仓库
  git remote  （只能查看远程仓库的名字）#查看单个仓库
  git  remote show [remote-branch-name]
  
  #新建远程仓库
  git remote add [branchname]  [url]
  
  #修改远程仓库
  git remote rename [oldname] [newname]
  
  #删除远程仓库
  git remote rm [remote-name]
  
  #获取远程仓库数据
  git fetch [remote-name] (获取仓库所有更新，但不自动合并当前分支)
  git pull (获取仓库所有更新，并自动合并到当前分支)
  
  #上传数据，如git push origin master
  git push [remote-name] [branch]
  ```

  

